package de.linkvt.ontobench.web.converters.parameter;

import de.linkvt.ontobench.features.Feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Base class for mapping parameters to features.
 */
@Component
public class FeatureParameterMapping {
  private Map<String, Feature> featureMap = new HashMap<>();

  @Autowired
  public FeatureParameterMapping(ApplicationContext context) {
    Collection<Feature> features = context.getBeansOfType(Feature.class).values();

    for (Feature feature : features) {
      register(feature.getToken(), feature);
    }
  }

  private void register(String parameter, Feature feature) {
    if (featureMap.containsKey(parameter)) {
      throw new IllegalArgumentException("Parameter \"" + parameter + "\" already in use.");
    }
    featureMap.put(parameter, feature);
  }

  public Feature get(String parameter) {
    return featureMap.get(parameter);
  }

  public List<Feature> get(Collection<String> parameters) {
    return parameters.stream()
        .map(this::get)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  public List<Feature> getAll() {
    List<Feature> features = featureMap.values().stream().collect(Collectors.toList());
    return Collections.unmodifiableList(features);
  }
}
