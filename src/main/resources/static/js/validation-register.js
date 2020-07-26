$(document).ready(function () {
    $(".text-danger").hide()
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content")

    $('#register-btn').click(function (event) {
        let $password1 = $('#password1');
        let $password2 = $('#password2');
        let $passwordHelp = $('#passwordHelp');
        event.preventDefault()

        let pass1 = $password1.val();
        let pass2 = $password2.val();
        if (pass1 !== pass2) {
            $password1.alert()
            $password2.alert()
            $passwordHelp.show()
        } else {
            $passwordHelp.hide()
            let headers = {}
            headers[header] = token
            $.ajax({
                url: '/register',
                data: JSON.stringify({
                    username: $("[name='username']").val(),
                    name: $("[name='name']").val(),
                    surname: $("[name='surname']").val(),
                    birthDate: $("[name='birthDate']").val(),
                    password: pass1,
                    roles: [{
                        name: $("[name='roles']").val()
                    }]
                }),
                headers: headers,
                contentType: 'application/json',
                method: 'POST',
                success: function () {
                    $(location).attr('href', '/');
                },
                statusCode: {
                    400: function (data) {
                        let errors = data.responseJSON;
                        $(".text-danger").hide()
                        $(".form-control").removeClass('form-control-danger')
                        Object.keys(errors).forEach(name => {
                            $("[name='" + name + "']").addClass('form-control-danger')
                            let helper = $("#" + name + "Help");
                            helper.text(errors[name])
                            helper.show()
                        })
                    },
                    200: function () {
                        $(".form-control").removeClass('form-control-danger')
                        $(".text-danger").hide()
                    }
                }
            })
        }
    })
})