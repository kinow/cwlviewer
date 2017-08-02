package org.commonwl.view.workflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.commonwl.view.cwl.CWLToolStatus;
import org.springframework.data.annotation.Id;

/**
 * A workflow pending completion of cwltool
 */
@JsonIgnoreProperties(value = {"id", "tempRepresentation"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueuedWorkflow {

    // ID for database
    @Id
    public String id;

    // Very barebones workflow to build loading thumbnail and overview
    private Workflow tempRepresentation;

    // Cwltool details
    private CWLToolStatus cwltoolStatus = CWLToolStatus.DOWNLOADING;
    private String cwltoolVersion = "";
    private String message;

    public String getId() {
        return id;
    }

    public Workflow getTempRepresentation() {
        return tempRepresentation;
    }

    public void setTempRepresentation(Workflow tempRepresentation) {
        this.tempRepresentation = tempRepresentation;
    }

    public CWLToolStatus getCwltoolStatus() {
        return cwltoolStatus;
    }

    public void setCwltoolStatus(CWLToolStatus cwltoolStatus) {
        this.cwltoolStatus = cwltoolStatus;
    }

    public String getCwltoolVersion() {
        return cwltoolVersion;
    }

    public void setCwltoolVersion(String cwltoolVersion) {
        this.cwltoolVersion = cwltoolVersion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
