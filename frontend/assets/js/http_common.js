import axios from 'axios'
import store from "../../store";
import router from "../../router";
export const createHttpWithHeaders = function(headers){


    var callback = function(){
        store.dispatch('setLoading', false)
    }

    var axiosInstance =  axios.create({
        baseURL: '/',
        headers: headers
    })


    axiosInstance.interceptors.request.use(
        conf =>{
            store.dispatch('setLoading', true)
            return conf
        },
        error => {
            callback()
            return Promise.reject(error)
        })

    axiosInstance.interceptors.response.use(
        response =>{
            callback()
            return response
        },
        error => {
            callback()
            if(error.toString().indexOf('401') !== -1) {
                //console.log('Token has expired')
                store.dispatch('setSessionExpired', true)
                router.push({name:'login'})
            }
            return Promise.reject(error)
        })

    return axiosInstance
}
