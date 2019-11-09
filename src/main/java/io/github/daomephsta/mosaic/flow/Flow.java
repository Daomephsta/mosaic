package io.github.daomephsta.mosaic.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.daomephsta.mosaic.MosaicWidget;
import io.github.daomephsta.mosaic.ParentWidget;

public class Flow extends MosaicWidget implements ParentWidget
{
	private final Direction direction;
	private final List<MosaicWidget> children = new ArrayList<>();
	private final Map<MosaicWidget, FlowLayoutData> childLayoutData = new HashMap<>();

	public Flow(Direction direction)
	{
		this.direction = direction;
	}

	public Flow add(MosaicWidget mosaicWidget, FlowLayoutData layoutData)
	{
	    children.add(mosaicWidget);
	    childLayoutData.put(mosaicWidget, layoutData);
	    return this;
	}

	public Iterable<MosaicWidget> getChildren()
	{
		return children;
	}

	@Override
    public void layoutChildren()
	{
		if (children.size() == 0)
			return;
		int availableSpace = direction.getAvailableSpace(this);
		int nextChildCoord = direction.getStartCoord(this);
		for (MosaicWidget child : children)
		{
			direction.setFixedCoord(child, direction.getFixedCoord(this));
			direction.setFixedDimension(child,
					direction.getFixedDimension(this));
			direction.setVariableCoord(child, nextChildCoord);
			int childSize = childLayoutData.get(child).computeSize(availableSpace);
			direction.setVariableDimension(child, childSize);
			nextChildCoord += childSize;
			if (child instanceof ParentWidget)
				((ParentWidget) child).layoutChildren();
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
