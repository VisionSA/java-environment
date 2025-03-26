/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.print;

import java.io.Serializable;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;

/**
 *
 * @author sporta
 */
public class ConfiguracionTrabajo implements Serializable{

    private Integer cantidadCopias;

    private OrientationRequested orientacion;

    private PageRanges pageRanges;

    private MediaSizeName mediaSizeName;

    private MediaPrintableArea mediaPrintableArea;

    private JobName jobName;
        
    public Integer getCantidadCopias() {
        return cantidadCopias;
    }

    public void setCantidadCopias(Integer cantidadCopias) {
        this.cantidadCopias = cantidadCopias;
    }

    public OrientationRequested getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientationRequested orientacion) {
        this.orientacion = orientacion;
    }

    public void setPageRanges(PageRanges pageRanges) {
        this.pageRanges = pageRanges;
    }

    public PageRanges getPageRanges() {
        return pageRanges;
    }

    public MediaSizeName getMediaSizeName() {
        return mediaSizeName;
    }

    public void setMediaSizeName(MediaSizeName mediaSizeName) {
        this.mediaSizeName = mediaSizeName;
    }

    public void setMediaSizeCustom(float w, float h){
        mediaSizeName = MediaSize.findMedia(w, h, MediaSize.MM);
    }

    public MediaPrintableArea getMediaPrintableArea() {
        return mediaPrintableArea;
    }

    public void setMediaPrintableArea(MediaPrintableArea mediaPrintableArea) {
        this.mediaPrintableArea = mediaPrintableArea;
    }

    public JobName getJobName() {
        return jobName;
    }

    public void setJobName(JobName jobName) {
        this.jobName = jobName;
    }


}
