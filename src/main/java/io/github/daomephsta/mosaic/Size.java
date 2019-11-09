package io.github.daomephsta.mosaic;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Size
{
	private static final Pattern PATTERN = Pattern
			.compile("(?<magnitude>\\d+(?:\\.\\d+)?)(?<unit>[a-z]+)?");

	private final double magnitude;
	private final Unit unit;

	private Size(double magnitude, Unit unit)
	{
		unit.validate(magnitude);
		this.magnitude = magnitude;
		this.unit = unit;
	}

	public static Size pixels(double pixels)
	{
		return new Size(pixels, Unit.PIXELS);
	}

	public static Size percentage(double percentage)
	{
		return new Size(percentage, Unit.PERCENTAGE);
	}

	public static Size parse(String s) throws ParseException
	{
		Matcher matcher = PATTERN.matcher(s);
		if (matcher.matches())
		{
			try
			{
				double magnitude = Double
						.parseDouble(matcher.group("magnitude"));
				String unitString = matcher.group("unit");
				Unit unit = Unit.UNIT_BY_ABBREVIATION.get(unitString);
				if (unit == null)
					throw new ParseException(unitString);
				return new Size(magnitude, unit);
			} catch (IllegalArgumentException nfe)
			{
				throw new ParseException(nfe.getMessage());
			}
		}
		throw new ParseException("Could not parse " + s + " as a size. Regex "
				+ PATTERN + " did not match fully.");
	}

	public double toAbsolute(double max)
	{
		return unit.toAbsolute(magnitude, max);
	}

	private enum Unit
	{
		PIXELS("px")
		{
			@Override
			public double toAbsolute(double magnitude, double max)
			{
				return magnitude;
			}
		},
		PERCENTAGE("%")
		{
			@Override
			public double toAbsolute(double magnitude, double max)
			{
				return magnitude / 100.0D * max;
			}

			@Override
			void validate(double magnitude)
			{
				if (magnitude > 100)
					throw new IllegalArgumentException(
							"Magnitude is greater than 100%");
			}
		};

		private static final Map<String, Unit> UNIT_BY_ABBREVIATION;
		static
		{
			UNIT_BY_ABBREVIATION = Arrays.stream(values())
					.collect(Collectors.toMap(u -> u.abbreviation, u -> u));
		}

		private final String abbreviation;

		private Unit(String abbreviation)
		{
			this.abbreviation = abbreviation;
		}

		public abstract double toAbsolute(double magnitude, double max);
		void validate(double magnitude)
		{
		}
	}
}
