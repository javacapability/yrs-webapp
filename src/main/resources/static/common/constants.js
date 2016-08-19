angular.module('constants',[])
    .constant('webServices', {
        //serviceHost: '$()', //server host URL
        serviceHost: '/yrs',

        //Update with correct service methods ex: loginEndpoint: '/login'
        loginEndpoint: '/loginWeb',
        resetEndpoint: '/reset',
        loadmainEndpoint: '/loadMain',
        logoutEndpoint: '/logoutWeb',

        userEndpoint: '/getUserInfo',
        userListEndpoint: '/getUserList',
        userSaveEndpoint: '/register',
        userEditEndpoint: '/update',
        userDeleteEndpoint: '/delete',

        questionEndpoint: '/getQuestion',
        questionListEndpoint: '/getQuestionList',
        questionSaveEndpoint: '/question/create',
        questionEditEndpoint: '/question/update',
        questionDeleteEndpoint: '/question/delete',
        
        assessmentListEndpoint: '/getAssessmentList',
        assessmentGetEndpoint: '/getAssessment',
        
        parametersListEndpoint: '/syspar/all',
        parametersUpdateEndpoint: '/syspar/update'
        
    });