package io.github.daomephsta.mosaic;

public class Size
{
    private SizeConstraint constraint;
    private int minSize,
                maxSize;

    public Size()
    {
        this.constraint = SizeConstraint.DEFAULT;
        this.minSize = 1;
        this.maxSize = Integer.MAX_VALUE;
    }

    public int computeSize(double parentSize)
    {
        // Clamp within [minSize, maxSize] and round
        return (int) Math.round(Math.min(Math.max(constraint.toAbsolute(parentSize), minSize), maxSize));
    }

    public Size setSizeConstraint(SizeConstraint constraint)
    {
        this.constraint = constraint;
        return this;
    }

    public Size setMinSize(int minSize)
    {
        this.minSize = minSize;
        return this;
    }

    public Size setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
        return this;
    }
}
