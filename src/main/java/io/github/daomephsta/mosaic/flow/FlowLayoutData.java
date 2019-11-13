package io.github.daomephsta.mosaic.flow;

import io.github.daomephsta.mosaic.Size;

public class FlowLayoutData
{
    private Size size = Size.DEFAULT;
    private int minSize = 1,
                maxSize = Integer.MAX_VALUE;

    public int computeSize(double parentSize)
    {
        // Clamp within [minSize, maxSize] and round
        return (int) Math.round(Math
                .min(Math.max(size.toAbsolute(parentSize), minSize), maxSize));
    }

    public FlowLayoutData setSize(Size size)
    {
        this.size = size;
        return this;
    }

    public FlowLayoutData setMinSize(int minSize)
    {
        this.minSize = minSize;
        return this;
    }

    public FlowLayoutData setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
        return this;
    }
}
