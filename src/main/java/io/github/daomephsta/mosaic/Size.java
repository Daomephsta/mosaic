package io.github.daomephsta.mosaic;

public abstract class Size
{
    public static final Size DEFAULT = new Size()
    {
        @Override
        public double toAbsolute(double max)
        {
            return 0; //Guarantees that minium size will kick in
        }
    };

	public static Size pixels(double pixels)
	{
		return UnitSize.pixels(pixels);
	}

	public static Size percentage(double percentage)
	{
		return UnitSize.percentage(percentage);
	}

	public static Size parse(String s) throws ParseException
	{
	    if (s.equals("default"))
	        return DEFAULT;
		Size size = UnitSize.parse(s);
		if (size == null)
		    throw new ParseException("Could not parse " + s + " as a size.");
        return size;
	}

	public abstract double toAbsolute(double max);
}
