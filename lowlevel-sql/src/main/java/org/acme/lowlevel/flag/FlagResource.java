package org.acme.lowlevel.flag;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path( "FLAG" )
public class FlagResource
{

  @Inject
  FeatureFlags featureFlags;

  @GET
  public FeatureFlags featureFlags() {
    return featureFlags;
  }
}
