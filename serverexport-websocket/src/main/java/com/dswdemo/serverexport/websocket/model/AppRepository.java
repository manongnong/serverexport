package com.dswdemo.serverexport.websocket.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppRepository extends JpaRepository<AppInfo,Long> {

}
