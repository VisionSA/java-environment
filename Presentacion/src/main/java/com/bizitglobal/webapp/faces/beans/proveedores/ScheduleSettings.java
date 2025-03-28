package com.bizitglobal.webapp.faces.beans.proveedores;

import java.io.Serializable;

import org.apache.myfaces.custom.schedule.model.ScheduleModel;


/**
 * Handler class that contains the settings of the customizable schedule example.
 * 
 * @author Jurgen Lust (latest modification by $Author$)
 * @version $Revision$
 */
public class ScheduleSettings implements Serializable
{
	private static final long serialVersionUID = -8547428935814382762L;
	private int compactMonthRowHeight;
	private int compactWeekRowHeight;
	private int detailedRowHeight;
	private String headerDateFormat;
	private ScheduleModel model;
	private boolean readonly;
	private String theme;
	private boolean tooltip;
	private int visibleEndHour;
	private int visibleStartHour;
	private int workingEndHour;
	private int workingStartHour;
	private boolean renderZeroLength;
	private boolean expandToFitEntries;

	
	public int getCompactMonthRowHeight()
	{
		return compactMonthRowHeight;
	}


	public int getCompactWeekRowHeight()
	{
		return compactWeekRowHeight;
	}


	public int getDetailedRowHeight()
	{
		return detailedRowHeight;
	}


	public String getHeaderDateFormat()
	{
		return headerDateFormat;
	}


	public ScheduleModel getModel()
	{
		return model;
	}


	public String getTheme()
	{
		return theme;
	}


	public int getVisibleEndHour()
	{
		return visibleEndHour;
	}


	public int getVisibleStartHour()
	{
		return visibleStartHour;
	}


	public int getWorkingEndHour()
	{
		return workingEndHour;
	}


	public int getWorkingStartHour()
	{
		return workingStartHour;
	}


	public boolean isReadonly()
	{
		return readonly;
	}


	public boolean isTooltip()
	{
		return tooltip;
	}


	public String save()
	{
		model.refresh();
		return "success";
	}


	public void setCompactMonthRowHeight(int compactMonthRowHeight)
	{
		this.compactMonthRowHeight = compactMonthRowHeight;
	}


	public void setCompactWeekRowHeight(int compactWeekRowHeight)
	{
		this.compactWeekRowHeight = compactWeekRowHeight;
	}


	public void setDetailedRowHeight(int detailedRowHeight)
	{
		this.detailedRowHeight = detailedRowHeight;
	}


	public void setHeaderDateFormat(String headerDateFormat)
	{
		this.headerDateFormat = headerDateFormat;
	}


	public void setModel(ScheduleModel model)
	{
		this.model = model;
	}


	public void setReadonly(boolean readonly)
	{
		this.readonly = readonly;
	}


	public void setTheme(String theme)
	{
		this.theme = theme;
	}


	public void setTooltip(boolean tooltip)
	{
		this.tooltip = tooltip;
	}


	public void setVisibleEndHour(int visibleEndHour)
	{
		this.visibleEndHour = visibleEndHour;
	}


	public void setVisibleStartHour(int visibleStartHour)
	{
		this.visibleStartHour = visibleStartHour;
	}


	public void setWorkingEndHour(int workingEndHour)
	{
		this.workingEndHour = workingEndHour;
	}


	public void setWorkingStartHour(int workingStartHour)
	{
		this.workingStartHour = workingStartHour;
	}


	/**
	 * @return Returns the renderZeroLength.
	 */
	public boolean isRenderZeroLength()
	{
		return renderZeroLength;
	}


	/**
	 * @param renderZeroLength
	 *            The renderZeroLength to set.
	 */
	public void setRenderZeroLength(boolean renderZeroLength)
	{
		this.renderZeroLength = renderZeroLength;
	}


	/**
	 * @return Returns the expandToFitEntries.
	 */
	public boolean isExpandToFitEntries()
	{
		return expandToFitEntries;
	}


	/**
	 * @param expandToFitEntries
	 *            The expandToFitEntries to set.
	 */
	public void setExpandToFitEntries(boolean expandToFitEntries)
	{
		this.expandToFitEntries = expandToFitEntries;
	}


	public String getMode() {
		return String.valueOf(getModel().getMode());
	}


	public void setMode(String mode) {
		getModel().setMode(Integer.valueOf(mode).intValue());
	}
}
