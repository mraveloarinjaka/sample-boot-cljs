(set-env!
 :source-paths #{"src/clj" "src/cljs"}
 :resource-paths #{"html"}

 :dependencies '[[org.clojure/clojure        "1.8.0"                ]
                 [org.clojure/clojurescript  "1.9.946"              ]
                 [adzerk/boot-cljs           "2.1.4"                ]
                 [adzerk/boot-cljs-repl      "0.3.3"  :scope  "test"] ;; needed by boot-cljs-repl
                 [adzerk/boot-reload         "0.5.2"  :scope  "test"] ;; needed by boot-cljs-repl
                 [com.cemerick/piggieback    "0.2.2"  :scope  "test"] ;; needed by boot-cljs-repl
                 [weasel                     "0.7.0"  :scope  "test"] ;; needed by boot-cljs-repl
                 [org.clojure/tools.nrepl    "0.2.12" :scope  "test"] ;; needed by boot-cljs-repl
                 [pandeiro/boot-http         "0.8.3"                ]
                 ])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[adzerk.boot-reload :refer [reload]]
         '[pandeiro.boot-http :refer [serve]])

(def OUTPUT-DIR "target")

(deftask cljs-devtools
  "setup clojurescript dev tools"
  []
  (merge-env! :dependencies '[[binaryage/devtools "0.9.10" :scope "test"]])
  (task-options! cljs #(update-in % [:compiler-options :preloads] conj 'devtools.preload))
  identity)


(deftask dev
  "Launch Immediate Feedback Development Environment"
  []
  (comp
    (watch)
    (speak)
    (cljs-devtools)
    (reload) ;; always before cljs task
    (cljs-repl) ;; always before cljs task
    (cljs :optimizations :none)
    (target :dir #{OUTPUT-DIR})
    (serve :dir OUTPUT-DIR
           :init 'my-namespace.bootstrap/jetty-init)))
