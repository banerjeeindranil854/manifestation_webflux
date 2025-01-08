package com.mtm.manifestation.v1.delegate;

import com.mtm.manifestation.v1.api.AvatarApiDelegate;
import com.mtm.manifestation.v1.dto.FetchAvatarResponse;
import com.mtm.manifestation.v1.service.AvaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AvatarApiDelegateImpl implements AvatarApiDelegate {
 @Autowired
 private AvaterService AvaterService;
 @Override
 public ResponseEntity<FetchAvatarResponse> avatarMSISDNGet(String MSISDN) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(AvaterService.getAvater());
    }


}
