(ns scrabble-score
  (require [clojure.string :as str]))

(def letter-values
  {1  #{\A \E \I \O \U \L \N \R \S \T}
   2  #{\D \G}
   3  #{\B \C \M \P}
   4  #{\F \H \V \W \Y}
   5  #{\K}
   8  #{\J \X}
   10 #{\Q \Z}})

(defn value-if-contains [letter [value letters]]
  (if (contains? letters letter) value 0))

(defn score-letter [str]
  (let [[letter & shit] (str/upper-case str)]
    (reduce + (map (partial value-if-contains letter) letter-values))))

(defn score-word [word]
  (reduce + (map score-letter word)))