package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.cases.dao.AtupTestResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtupTestResultService {
    @Autowired
    AtupTestResultDao dao;

}
