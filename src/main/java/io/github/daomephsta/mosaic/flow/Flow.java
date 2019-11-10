package io.github.daomephsta.mosaic.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.daomephsta.mosaic.MosaicWidget;
import io.github.daomephsta.mosaic.ParentWidget;

public class Flow<E extends MosaicWidget> extends MosaicWidget implements ParentWidget
{
	private final Direction direction;
	private final List<E> children = new ArrayList<>();
	private final Map<E, FlowLayoutData> childLayoutData = new HashMap<>();

	public Flow(Direction direction)
	{
		this.direction = direction;
	}

	public Flow<E> add(E mosaicWidget, FlowLayoutData layoutData)
	{
	    children.add(mosaicWidget);
	    childLayoutData.put(mosaicWidget, layoutData);
	    return this;
	}

	public Iterable<E> getChildren()
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
			int childSize = childLayoutData.get(child).computeSize(availableSpace);
			direction.setLayoutParameters(child, direction.getFixedCoord(this), direction.getFixedDimension(this),
			    nextChildCoord, childSize);
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
            void setLayoutParameters(MosaicWidget widget, int fixedCoord, int fixedDimension,
                int variableCoord, int variableDimension)
			{

		        int y = fixedCoord;
		        int height = fixedDimension;
		        int x = variableCoord;
		        int width = variableDimension;
		        widget.setLayoutParameters(x, y, width, height);
			}

			@Override
			int getAvailableSpace(Flow<?> flow)
			{ return flow.width(); }

			@Override
			int getFixedCoord(Flow<?> flow)
			{ return flow.y(); }

			@Override
			int getFixedDimension(Flow<?> flow)
			{ return flow.height(); }

			@Override
			int getStartCoord(Flow<?> flow)
			{ return flow.x(); }
		},
		VERTICAL
		{
		    @Override
            void setLayoutParameters(MosaicWidget widget, int fixedCoord, int fixedDimension,
		        int variableCoord, int variableDimension)
			{
		        int x = fixedCoord;
		        int width = fixedDimension;
		        int y = variableCoord;
		        int height = variableDimension;
		        widget.setLayoutParameters(x, y, width, height);
			}

			@Override
			int getAvailableSpace(Flow<?> flow)
			{ return flow.height(); }

			@Override
			int getFixedCoord(Flow<?> flow)
			{ return flow.x(); }

			@Override
			int getFixedDimension(Flow<?> flow)
			{ return flow.width(); }

			@Override
			int getStartCoord(Flow<?> flow)
			{ return flow.y(); }
		};

		abstract void setLayoutParameters(MosaicWidget widget, int fixedCoord, int fixedDimension,
		    int variableCoord, int variableDimension);
		abstract int getAvailableSpace(Flow<?> flow);
		abstract int getStartCoord(Flow<?> flow);
		abstract int getFixedCoord(Flow<?> flow);
		abstract int getFixedDimension(Flow<?> flow);
	}
}
