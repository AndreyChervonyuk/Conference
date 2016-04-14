package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface DocumentsService extends GenericService<Document, Integer> {
}
