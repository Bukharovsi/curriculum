function themeSelection(element) {
    var etpId = $('#id').val()
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
            teacherElement.val(data.teacherId).change()
        },
        error: function (e) {
            console.log(e)
            teacherElement.val("").change()
        }
    });
}

function getTeacherElementByTheme(themeElement) {
    var idAsArray = themeElement.id.split('.')
    idAsArray[idAsArray.length - 1] = 'teacherId'
    var teacherId = idAsArray.join('\\.')

    return $('#' + teacherId)
}