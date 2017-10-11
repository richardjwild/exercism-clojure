(ns crypto-square
  (require [clojure.string :as str]))

(defn normalize-plaintext [plaintext]
  (-> (filter #(Character/isLetterOrDigit %) plaintext) str/join str/lower-case))

(defn square-size [plaintext]
  (-> (count plaintext) Math/sqrt Math/ceil int))

(defn plaintext-segments [plaintext]
  (let [normalized (normalize-plaintext plaintext),
        size (square-size normalized),
        partitioned (partition-all size normalized)]
    (map str/join partitioned)))

(defn pad [size segment]
  (take size (concat segment (repeat \ ))))

(defn pad-all [size segments]
  (map (partial pad size) segments))

(defn join-then-trim [& characters]
  (-> characters str/join str/trim))

(defn ciphertext-segments [plaintext]
  (let [segments (plaintext-segments plaintext),
        pad-size (-> segments first count),
        segments-padded (pad-all pad-size segments)]
    (apply map join-then-trim segments-padded)))

(defn ciphertext [plaintext]
  (str/join (ciphertext-segments plaintext)))

(defn normalize-ciphertext [plaintext]
  (str/join (interpose " " (ciphertext-segments plaintext))))
