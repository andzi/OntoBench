package de.linkvt.bachelor.features.classes.cardinalities.data;

import de.linkvt.bachelor.features.Feature;
import de.linkvt.bachelor.features.FeatureCategory;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.springframework.stereotype.Component;

@Component
public class OwlDataMinCardinalityFeature extends Feature {
  @Override
  public void addToOntology() {
    OWLDataProperty property = factory.getOWLDataProperty(IRI.create("dataMinCardinality"));
    OWLDatatype datatype = OWL2Datatype.XSD_BOOLEAN.getDatatype(factory);
    addToGenericDomainAndNewRange(property, datatype);

    OWLClass range = featurePool.getExclusiveClass("DataMinCardinalityRange");
    OWLDataExactCardinality cardinality = factory.getOWLDataExactCardinality(3, property);
    OWLAxiom axiom = factory.getOWLSubClassOfAxiom(range, cardinality);
    addAxiomToOntology(axiom);
  }

  @Override
  public String getName() {
    return "owl:minCardinality (Data Property)";
  }

  @Override
  public String getToken() {
    return "mincardinalitydata";
  }

  @Override
  public FeatureCategory getCategory() {
    return FeatureCategory.CLASS;
  }
}