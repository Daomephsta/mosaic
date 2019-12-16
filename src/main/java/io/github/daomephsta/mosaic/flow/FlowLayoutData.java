package io.github.daomephsta.mosaic.flow;

import io.github.daomephsta.mosaic.Size;
import io.github.daomephsta.mosaic.SizeConstraint;

public class FlowLayoutData
{
    private final Size size;

    public FlowLayoutData(Size size)
    {
        this.size = size;
    }

    public FlowLayoutData()
    {
        this(new Size());
    }

    public int computeSize(double parentSize)
    {
        return size.computeSize(parentSize);
    }

    public Size setSizeConstraint(SizeConstraint constraint)
    {
        return size.setSizeConstraint(constraint);
    }

    public Size setMinSize(int minSize)
    {
        return size.setMinSize(minSize);
    }

    public Size setMaxSize(int maxSize)
    {
        return size.setMaxSize(maxSize);
    }
}
