package com.edu.chdtu.conference.dao.impl;


import com.edu.chdtu.conference.dao.ImageDao;
import com.edu.chdtu.conference.model.Image;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends GenericDaoImpl<Image, Integer> implements ImageDao {

    public ImageDaoImpl() {
        super(Image.class);
    }

}
