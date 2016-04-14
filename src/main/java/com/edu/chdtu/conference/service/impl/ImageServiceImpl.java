package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.model.Image;
import com.edu.chdtu.conference.service.ImageService;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.dao.ImageDao;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends GenericServiceImpl<Image, Integer> implements ImageService {

    private ImageDao imageDao;

    @Autowired
    public ImageServiceImpl(GenericDao<Image, Integer> genericDao) {
        super(genericDao);
        this.imageDao = (ImageDao) genericDao;
    }

}
