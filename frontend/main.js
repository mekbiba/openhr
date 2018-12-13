import Vue from 'vue'
import i18n from './i18n'
import store from './store'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import VueProgressBar from 'vue-progressbar'

require('bootstrap/dist/css/bootstrap.css')
require('bootstrap-vue/dist/bootstrap-vue.css')


const options = {
    color: '#bffaf3',
    failedColor: '#874b4b',
    thickness: '5px',
    transition: {
        speed: '0.2s',
        opacity: '0.6s',
        termination: 300
    },
    autoRevert: true,
    location: 'left',
    inverse: false
}

Vue.use(VueProgressBar, options)
Vue.use(BootstrapVue)

Vue.config.productionTip = true
new Vue({
  el: '#app',
  store,
  router, i18n,
  template: '<App/>',
  components: { App }
});
