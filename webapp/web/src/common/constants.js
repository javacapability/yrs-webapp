angular.module('constants',[])
    .constant('webServices', {
        //serviceHost: 'http://localhost/yrs-app', //???? server host URL
        serviceHost: '',
        
        //Update with correct paths
        loginServicePath: '_test/users.json',
        userServicePath: '_test/users.json',
        questionServicePath: '_test/questions.json',
        assessmentServicePath: '_test/assessment.json',
        parametersServicePath: '_test/params.json',
        
        //Update with correct service methods ex: loginEndpoint: '/login'
        loginEndpoint: '',
        
        userListEndpoint: '',
        userSaveEndpoint: '',
        userEditEndpoint: '',
        userDeleteEndpoint: '',
        
        questionListEndpoint: '',
        questionSaveEndpoint: '',
        questionEditEndpoint: '',
        questionDeleteEndpoint: '',
        
        assessmentListEndpoint: '',
        assessmentSaveEndpoint: '',
        assessmentEditEndpoint: '',
        assessmentDeleteEndpoint: '',
        
        parametersGetEndpoint: '',
        parametersSaveEndpoint: ''
        
    });