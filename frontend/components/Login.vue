<template>

</template>
<script>
    import {getHttpAuthenticated} from '../assets/js/http_with_token'
    var context = {}
    export default {
        name: 'Login',
        data(){
            return {
                welcome : "Welcome to SandBox",
                employeeList: []
            }
        },
        created(){
            context = this
            context.loadAllEmployees()
        },
        methods:{
            loadAllEmployees(){
                getHttpAuthenticated()
                    .get('/rest-api/hr/employee/all')
                    .then((response)=>{
                        context.employeeList = response.data
                    })
                    .catch((error)=>{
                        alert(error)
                    })
            }
        }

    }
</script>
