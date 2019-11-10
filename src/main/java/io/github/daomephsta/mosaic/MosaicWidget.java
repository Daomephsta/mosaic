package io.github.daomephsta.mosaic;

public abstract class MosaicWidget
{
	private int x, y, width, height;

	@Override
	public String toString()
	{
		return String.format("%s [x=%s, y=%s, width=%s, height=%s]",
				this.getClass().getSimpleName(), x, y, width, height);
	}

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }

    public int width()
    {
        return width;
    }

    public int height()
    {
        return height;
    }

    public void setLayoutParameters(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
