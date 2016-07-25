angular.module('constants',[])
    .constant('webServices', {
        //serviceHost: 'http://localhost/yrs-app', //???? server host URL
        serviceHost: 'http://localhost:8080/yrs',
        
        //Update with correct paths
        loginServicePath: '',
        userServicePath: '',
        questionServicePath: '',
        assessmentServicePath: '',
        parametersServicePath: '',
        
        //Update with correct service methods ex: loginEndpoint: '/login'
        loginEndpoint: '/login',
        
        userListEndpoint: '/users',
        userSaveEndpoint: '/register',
        userEditEndpoint: '/update',
        userDeleteEndpoint: '/delete',
        
        questionListEndpoint: '/getQuestionList',
        questionSaveEndpoint: '/question/create',
        questionEditEndpoint: '/question/update',
        questionDeleteEndpoint: '/question/delete',
        
        assessmentListEndpoint: '/getAssessmentList',
        assessmentGetEndpoint: '/getAssessment',
        
        parametersGetEndpoint: '',
        parametersSaveEndpoint: ''
        
    });