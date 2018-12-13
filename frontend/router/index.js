import Vue from 'vue'
import store from '../store'
import Router from 'vue-router'
import Login from '../components/Login'


Vue.use(Router)

var router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'login',
            component: Login
        }
    ]
})

var hasPermission = function(path){
    var rightName = path.meta.requiresRight
    return store.state.persistedState.userNodeRoleRights.includes(rightName)
}
router.beforeEach((to, from, next) => {

    const authUser = store.state.persistedState.authToken
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (!authUser) {
            next('/')
        } else {
            if(from.name !== "login"){
                if(hasPermission(to)){
                    next()
                } else{
                    next('/')
                }
            }else{
                next()
            }
        }
    } else {
        next() // make sure to always call next()!
    }
})

export default router
