package org.acme.kafka;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;


@RegisterForReflection
public class UserVerionValidation
{
public final Long Id;
public final UUID version;


  public UserVerionValidation( Long id, UUID version )
    {
      this.Id =id;
      this.version = version;
    }





}
