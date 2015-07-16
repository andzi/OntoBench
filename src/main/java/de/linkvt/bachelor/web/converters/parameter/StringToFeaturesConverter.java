package de.linkvt.bachelor.web.converters.parameter;

import de.linkvt.bachelor.features.Feature;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Maps the query string to requested features.
 */
@Component
public class StringToFeaturesConverter implements Converter<String, List<Feature>> {

  private FeatureParameterMapping mapping;

  @Autowired
  public StringToFeaturesConverter(FeatureParameterMapping mapping) {
    this.mapping = mapping;
  }

  @Override
  public List<Feature> convert(String query) {
    if (StringUtils.isEmpty(query)) {
      return Collections.emptyList();
    }
    String lowerCaseQuery = query.toLowerCase();

    List<String> parameters = Arrays.asList(lowerCaseQuery.split(","));
    List<Feature> features = new ArrayList<>();

    for (String parameter : parameters) {
      Feature feature = mapping.get(parameter);

      if (feature != null) {
        features.add(feature);
      }
    }

    return Collections.unmodifiableList(features);
  }
}
