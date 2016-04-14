package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.DocumentsDao;
import com.edu.chdtu.conference.dao.ImageDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.Document;
import com.edu.chdtu.conference.model.Image;
import com.edu.chdtu.conference.model.User;
import com.edu.chdtu.conference.service.DocumentsService;
import com.edu.chdtu.conference.service.UserService;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import com.edu.chdtu.conference.util.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;

@Service
public class DocumentsServiceImpl extends GenericServiceImpl<Document, Integer> implements DocumentsService {

    private DocumentsDao documentsDao;
    private UserDao userDao;
    private ImageValidator imageValidator;
    private ImageDao imageDao;

    private static String UPLOAD_LOCATION = "C:\\temp\\";


    @Autowired
    public DocumentsServiceImpl(GenericDao<Document, Integer> genericDao) {
        super(genericDao);
        this.documentsDao = (DocumentsDao) genericDao;
    }

}
