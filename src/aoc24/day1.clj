(ns aoc24.day1
  (:require [clojure.string :as str]))

(def input
  (->> (slurp "resources/day1.txt")
      str/split-lines
      (map #(str/split % #"\s+"))))

(def input-left
  (sort (map #(Integer/parseInt %) (reduce #(conj %1 (nth %2 0)) [] input))))
(def input-right
  (sort (map #(Integer/parseInt %) (reduce #(conj %1 (nth %2 1)) [] input))))

(defn solve-part1
  "Get the first element of left and right list, compute their difference, and sum all the differences."
  [left-list right-list]
  (loop [left left-list
         right right-list
         acc []]
    (let [l (first left)
          r (first right)]
      (if (and (nil? l) (nil? r))
        acc
        (recur (rest left) (rest right) (conj acc (abs (- l r))))))))

(defn part1 [] (reduce + (solve-part1 input-left input-right)))

(defn solve-part2
  "For each number in the left list, multiply its value by the number of times it appears in the right list."
  [left-list right-list]
  (map (fn [x] (* x (count (filter #(= x %) right-list)))) left-list))

(defn part2 []
  (reduce + (solve-part2 input-left input-right)))
