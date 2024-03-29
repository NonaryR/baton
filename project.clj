(defproject baton "0.1.0"
  :dependencies [[org.clojure/clojure "1.9.0-alpha10"]
                 [org.clojure/clojurescript "1.9.562"]
                 [reagent "0.6.0"]
                 [re-frame "0.9.4"]
                 [re-frisk "0.4.5"]
                 [secretary "1.2.3"]
                 [cljs-ajax "0.6.0"]
                 [cljs-react-material-ui "0.2.44"]
                 [ring "1.5.1"]
                 [ring-basic-authentication "1.0.5"]
                 [http-kit "2.2.0"]


                 [org.clojure/test.check "0.9.0" :scope "test"]
                 [paren-soup "2.8.6" :scope "test"]
                 [compliment "0.3.4"]
                 [eval-soup "1.2.1"]
                 [cljsjs/codemirror "5.19.0-0" :scope "test"]
                 ]

  :plugins [[lein-cljsbuild "1.1.4"]]
  :min-lein-version "2.5.3"
  :source-paths ["src/clj"]
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  :figwheel {:css-dirs ["resources/public/css"]}
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]
                   [figwheel-sidecar "0.5.9"]
                   [com.cemerick/piggieback "0.2.1"]]
    :plugins      [[lein-figwheel "0.5.9"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "baton.core/mount-root"}
     :compiler     {:main                 baton.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            baton.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]})
