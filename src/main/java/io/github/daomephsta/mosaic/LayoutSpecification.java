package io.github.daomephsta.mosaic;

public class LayoutSpecification
{
    private Size size = null;
    private int minSize = 1,
                maxSize = Integer.MAX_VALUE;

    public int computeSize(double parentSize)
    {
        // Clamp within [minSize, maxSize] and round
        return (int) Math.round(Math
                .min(Math.max(size.toAbsolute(parentSize), minSize), maxSize));
    }

    public LayoutSpecification setSize(Size size)
    {
        this.size = size;
        return this;
    }

    public LayoutSpecification setMinSize(int minSize)
    {
        this.minSize = minSize;
        return this;
    }

    public LayoutSpecification setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
        return this;
    }
}
