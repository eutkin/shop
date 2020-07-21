$(document).ready(function () {


    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content")

    $('#slotTime-tab').on('shown.bs.tab', function (e){
        let specialityId = $('#select-speciality').val()
        let departmentId = $('#select-department').val()
        $.get('/slots/' + specialityId + '/' + departmentId, function (data) {
            $('#slot-container').append(data)
            $('.record-btn').click(function (event) {
                let $btn = $(event.target)
                let slotId = $btn.attr('slotId')
                let body = {slotId: slotId}
                let headers = {}
                headers[header] = token
                $.ajax({
                    url: '/api/v1/record',
                    data: JSON.stringify(body),
                    method: 'POST',
                    headers: headers,
                    contentType: 'application/json',
                    success: function (data) {
                        $('#speciality-tab').tab('show')
                        $('#recordModal').modal('hide')
                        $('#record-table').append(
                            '<tr><td>'+ data.dateTime + '</td><td></td><td>'+
                            data.speciality+'</td><td>'+data.doctor.name+'</td></tr>'
                        )
                        $('#slot-container').empty()

                    },
                    error: function () {
                        console.log('ERROR')
                    }
                })
            })
        })
    })

})