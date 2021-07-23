package com.stackroute.contentanalysingservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExternalApiResponse implements Serializable {
    private String type;
    private String programName;
    private String domain;
    private String concept;
    private List<Content> urls;

}
