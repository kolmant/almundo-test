package com.almundo.example.callcenter.services;

import org.springframework.stereotype.Service;

/**
 * Manages the Director employee handler role.
 * As an director does not have a superior, it
 * does not inherit the ScalableAssignor service
 * behaviour.
 */
@Service("directorService")
public class DirectorService extends AssignorService {

}
