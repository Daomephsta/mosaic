package io.github.daomephsta.mosaic;

public class EdgeSpacing
{
    private int left = 0,
                right = 0,
                top = 0,
                bottom = 0;

    public EdgeSpacing left(int left)
    {
        this.left = left;
        return this;
    }

    public int left()
    {
        return this.left;
    }

    public EdgeSpacing right(int right)
    {
        this.right = right;
        return this;
    }

    public int right()
    {
        return this.right;
    }

    public EdgeSpacing top(int top)
    {
        this.top = top;
        return this;
    }

    public int top()
    {
        return this.top;
    }

    public EdgeSpacing bottom(int bottom)
    {
        this.bottom = bottom;
        return this;
    }

    public int bottom()
    {
        return this.bottom;
    }

    public EdgeSpacing horizontal(int value)
    {
        this.left = this.right = value;
        return this;
    }

    public EdgeSpacing vertical(int value)
    {
        this.top = this.bottom = value;
        return this;
    }

    public EdgeSpacing all(int value)
    {
        this.left = this.right = this.top = this.bottom = value;
        return this;
    }
}
