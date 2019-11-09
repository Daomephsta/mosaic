package io.github.daomephsta.mosaic.test.manual;

import java.awt.Graphics;

import io.github.daomephsta.mosaic.Flow;
import io.github.daomephsta.mosaic.MosaicWidget;

public class TestFlow extends Flow implements Paintable
{
	public TestFlow(Direction direction)
	{
		super(direction);
	}

	@Override
	public void paint(Graphics g)
	{
		for (MosaicWidget child : getChildren())
		{
			if (child instanceof Paintable)
				((Paintable) child).paint(g);
			else
				throw new IllegalStateException("Cannot paint " + child);
		}
	}
}
