(ns binary-search-tree)

(defn value [tree]
  (tree :value))

(defn singleton [from]
  (hash-map :value from, :left nil, :right nil))

(defn insert
  ([value tree]
   (condp apply [value (tree :value)]
     <= (insert value tree :left)
     > (insert value tree :right)))
  ([value tree which-side]
   (if (nil? (tree which-side))
     (assoc tree which-side (singleton value))
     (assoc tree which-side (insert value (tree which-side))))))

(defn left [tree]
  (tree :left))

(defn right [tree]
  (tree :right))

(defn from-list [coll]
  (let [root (singleton (first coll)),
        to-binary-tree (fn [tree value] (insert value tree))]
    (reduce to-binary-tree root (rest coll))))

(defn to-list [tree]
  (if (nil? tree)
    '()
    (concat
      (to-list (left tree))
      (list (value tree))
      (to-list (right tree)))))
