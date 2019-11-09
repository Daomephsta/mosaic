package io.github.daomephsta.mosaic;

public abstract class MosaicWidget
{
	public int x, y, width, height;
	public final LayoutSpecification layoutSpec;

	protected MosaicWidget(LayoutSpecification layoutSpec)
	{
		this.layoutSpec = layoutSpec;
	}

	@Override
	public String toString()
	{
		return String.format("%s [x=%s, y=%s, width=%s, height=%s]",
				this.getClass().getSimpleName(), x, y, width, height);
	}
}
