package io.github.daomephsta.mosaic;

public class Flow extends MosaicWidget
{
	private final Direction direction;
	private final MosaicWidget[] children;

	public Flow(Direction direction, LayoutSpecification layoutSpec, MosaicWidget[] children)
	{
		super(layoutSpec);
		this.direction = direction;
		this.children = children;
	}

	public MosaicWidget[] getChildren()
	{
		return children;
	}

	public void layoutChildren()
	{
		if (children.length == 0)
			return;
		int availableSpace = direction.getAvailableSpace(this);
		int nextChildCoord = direction.getStartCoord(this);
		for (MosaicWidget child : children)
		{
			direction.setFixedCoord(child, direction.getFixedCoord(this));
			direction.setFixedDimension(child,
					direction.getFixedDimension(this));
			direction.setVariableCoord(child, nextChildCoord);
			int childSize = child.layoutSpec.computeSize(availableSpace);
			direction.setVariableDimension(child, childSize);
			nextChildCoord += childSize;
			if (child instanceof Flow)
				((Flow) child).layoutChildren();
		}
	}

	public enum Direction
	{
		HORIZONTAL
		{
			@Override
			public void setFixedCoord(MosaicWidget widget, int fixedCoord)
			{ widget.y = fixedCoord; }

			@Override
			public void setFixedDimension(MosaicWidget widget, int fixedDimension)
			{widget.height = fixedDimension; }

			@Override
			public void setVariableCoord(MosaicWidget widget, int variableCoord)
			{ widget.x = variableCoord; }

			@Override
			public void setVariableDimension(MosaicWidget widget, int variableDimension)
			{ widget.width = variableDimension; }

			@Override
			int getAvailableSpace(Flow flow)
			{ return flow.width; }

			@Override
			int getFixedCoord(Flow flow)
			{ return flow.y; }

			@Override
			int getFixedDimension(Flow flow)
			{ return flow.height; }

			@Override
			int getStartCoord(Flow flow)
			{ return flow.x; }
		},
		VERTICAL
		{
			@Override
			public void setFixedCoord(MosaicWidget widget, int fixedCoord)
			{ widget.x = fixedCoord; }

			@Override
			public void setFixedDimension(MosaicWidget widget, int fixedDimension)
			{ widget.width = fixedDimension; }

			@Override
			public void setVariableCoord(MosaicWidget widget, int variableCoord)
			{ widget.y = variableCoord; }

			@Override
			public void setVariableDimension(MosaicWidget widget, int variableDimension)
			{ widget.height = variableDimension; }

			@Override
			int getAvailableSpace(Flow flow)
			{ return flow.height; }

			@Override
			int getFixedCoord(Flow flow)
			{ return flow.x; }

			@Override
			int getFixedDimension(Flow flow)
			{ return flow.width; }

			@Override
			int getStartCoord(Flow flow)
			{ return flow.y; }
		};

		abstract void setFixedCoord(MosaicWidget widget, int fixedCoord);
		abstract void setFixedDimension(MosaicWidget widget, int fixedDimension);
		abstract void setVariableCoord(MosaicWidget widget, int variableCoord);
		abstract void setVariableDimension(MosaicWidget widget, int variableDimension);
		abstract int getAvailableSpace(Flow flow);
		abstract int getStartCoord(Flow flow);
		abstract int getFixedCoord(Flow flow);
		abstract int getFixedDimension(Flow flow);
	}
}
