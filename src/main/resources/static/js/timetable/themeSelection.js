$(function () {
    $('.t_ms').chosen();
})

function themeSelection(element) {
    var etpId = $('#createFromEtpId').val()
    var themeName = element.value
    var teacherElement = getTeacherElementByTheme(element)

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/timetable/teacherByTheme",
        data: {
            "etp_id": etpId,
            "theme": themeName
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            var teacherIds = data.map((t) => {return t.teacherId});
            teacherElement.val(teacherIds).change()
            teacherElement.trigger("chosen:updated");
        },
        error: function (e) {
            console.log(e)
        }
    });
}

function getTeacherElementByTheme(themeElement) {
    var idAsArray = themeElement.id.split('.')
    idAsArray[idAsArray.length - 1] = 'teacherIds'
    var teacherId = idAsArray.join('\\.')

    return $('#' + teacherId)
}