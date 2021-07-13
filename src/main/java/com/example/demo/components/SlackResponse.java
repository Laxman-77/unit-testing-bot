package com.example.demo.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackResponse {
    @JsonProperty("text")
    private String text;

    @JsonProperty("response_type")
    private String responseType;    // default response_type in slack is "ephemeral"

    @JsonProperty("attachments")
    private List<Attachment> attachments;

    public SlackResponse() {
        attachments = new ArrayList<>();
    }

    public SlackResponse(String text) {
        this.text = text;
    }

    public List getAttachments() {
        return attachments;
    }

    public SlackResponse setAttachments(List attachments) {
        this.attachments = attachments;
        return this;
    }

    public String getText() {
        return text;
    }

    public SlackResponse setText(String text) {
        this.text = text;
        return this;
    }

    public String getResponseType() {
        return responseType;
    }

    public SlackResponse setResponseType(String responseType) {
        this.responseType = responseType;
        return this;
    }



}
