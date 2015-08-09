package de.linkvt.bachelor.features.classes.setoperators.object;

import de.linkvt.bachelor.features.Feature;
import de.linkvt.bachelor.features.FeatureCategory;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.springframework.stereotype.Component;

@Component
public class OwlObjectComplementOfFeature extends Feature {
  @Override
  public void addToOntology() {
    OWLClass positive = featurePool.getExclusiveClass("Positive");
    OWLObjectComplementOf negative = factory.getOWLObjectComplementOf(positive);

    OWLClass owlClass = factory.getOWLClass(IRI.create("Negative"));

    OWLAxiom axiom = factory.getOWLEquivalentClassesAxiom(owlClass, negative);
    addAxiomToOntology(axiom);
  }

  @Override
  public String getName() {
    return "owl:complementOf (Class)";
  }

  @Override
  public String getToken() {
    return "complementclass";
  }

  @Override
  public FeatureCategory getCategory() {
    return FeatureCategory.CLASS;
  }
}