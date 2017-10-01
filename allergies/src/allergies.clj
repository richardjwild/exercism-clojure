(ns allergies)

(def allergen-scores
  {:eggs 1, :peanuts 2, :shellfish 4, :strawberries 8, :tomatoes 16, :chocolate 32, :pollen 64, :cats 128})

(defn allergic-to? [allergy-score allergen]
  (not (zero? (bit-and allergy-score (allergen-scores allergen)))))

(defn allergies [allergy-score]
  (filter (partial allergic-to? allergy-score) (keys allergen-scores)))
