$(function () {
    addMultiSelectToTeacherInput()
    bindOnChangesToCalculateEtpTotalHours()
})

function addMultiSelectToTeacherInput() {
    $(".t_ms").chosen({
        width: "100%"
    });
}

function bindOnChangesToCalculateEtpTotalHours() {
    $("input[id*='plan']").each(function (index, element) {
        element.onchange = calcTotal
        attachMask(element)
    })
    updateTotalAfterRenderPage()
}

/**
 *   Pass row which has cells with total sum.
 *  For each column run calculate total sum
 */
function updateTotalAfterRenderPage() {
    $("input[name*='etpTotalRow']").each(function (i, e) {
        var nameAsArray = e.name.split('.');
        var name = nameAsArray[nameAsArray.length-1]
        if(!/totlaHours/.test(name)) {
            calcTotalColumn(name)
        }
    })
}

function attachMask(element) {
    if (!isReadonlyField(element.name)) {
        isIntegerField(element.name) ? getIntegerMask().mask(element) : getFloatMask().mask(element)
    }
}

/**
 * @returns {*} Mask for input which value is float
 */
function getFloatMask() {
    return new Inputmask('decimal', {
        digits: 2,
        allowMinus: false,
        placeholder: "0.0",
    })
}

/**
 * @returns {*} Mask for input which value is integer
 */
function getIntegerMask() {
    return new Inputmask('integer', {
        allowMinus: false,
        placeholder: "0"
    })
}

/**
 *  Extract from DOM element id template for search row and column plan
 * and update each total row and column.
 *
 * @param e Example element id: omaModules0.plan.lectures, eaModules0.sections0.topics0.plan.lectures
 */
function calcTotal(e) {

    var idAsArray = e.target.id.split('.')
    var rowNameTemplate = idAsArray.slice(0, idAsArray.length -1).join('.')
    var columnNameTemplate = idAsArray[idAsArray.length - 1]

    calcTotalHours(rowNameTemplate)
    calcHourPerOneLerner(rowNameTemplate)
    calcTotalColumn(columnNameTemplate)
}

/**
 *  Find all cell by template column name and calc total row.
 * After start calculate "totalHours" column
 *
 * @param rowName
 */
function calcTotalHours(rowName) {
    var rowSelector = getSearchSelectorTemplate(rowName)

    var total = 0.0
    $(rowSelector).each(function (i, e) {
        if(isMainHoursField(e.name) && e.value) {
            total += parseFloat(e.value)
        }
    })

    var standard = $("input[id='" + rowName + ".standard']").val()
    var lernerCount = $("input[id='" + rowName + ".lernerCount']").val()
    var totalHoursRow = total + standard * lernerCount

    $("input[id='" + rowName + ".totalHours']").val(totalHoursRow)

    var totalHours = calcTotalColumn('totalHours')

    $('input[name="volumeInHours.total"]').val(totalHours)
}

function calcHourPerOneLerner(rowName) {
    var rowSelector = getSearchSelectorTemplate(rowName)

    var hoursPerOneLerner = 0.0

    $(rowSelector).each(function (i, e) {
        if(isMainHoursField(e.name) && e.value) {
            hoursPerOneLerner += parseFloat(e.value)
        }
    })

    $("input[id='" + rowName + ".hoursPerOneListener']").val(hoursPerOneLerner)

    calcTotalColumn('hoursPerOneListener')
}

/**
 * Find all cell by template column name and calc total sum
 *
 * @param colName
 */
function calcTotalColumn(colName) {
    var colSelector = getSearchSelectorTemplate(colName)

    var emaTotal = 0.0,
        omaTotal = 0.0,
        eaTotal = 0.0

    $(colSelector).each(function (i, e) {
        if(!e.value) {
            return
        }

        var emaModule = /emaModules/,
            omaModule = /omaModules/,
            eaModule = /eaModules/

        switch (true) {
            case emaModule.test(e.name):
                emaTotal += parseFloat(e.value)
                break
            case omaModule.test(e.name):
                omaTotal += parseFloat(e.value)
                break
            case eaModule.test(e.name):
                eaTotal += parseFloat(e.value)
                break
            default:
                break
        }
    })

    var total = emaTotal + omaTotal + eaTotal;
    $('#emaModuleTotalRow\\.' + colName).val(emaTotal)
    $('#omaModuleTotalRow\\.' + colName).val(omaTotal)
    $('#eaModuleTotalRow\\.' + colName).val(eaTotal)
    $('#etpTotalRow\\.' + colName).val(total)

    return total
}

function isMainHoursField(cellName) {
    return/lectures/.test(cellName) ||
        /practices/.test(cellName) ||
        /independentWorks/.test(cellName) ||
        /consultations/.test(cellName) ||
        /peerReviews/.test(cellName) ||
        /credits/.test(cellName) ||
        /others/.test(cellName)
}

function isReadonlyField(cellName) {
    return/hoursPerOneListener/.test(cellName) ||
        /totalHours/.test(cellName)
}

/**
 *  Check if cell is integer
 *
 * @param cellName
 * @returns {boolean}
 */
function isIntegerField(cellName) {
    return /lernerCount/.test(cellName) || /groupCount/.test(cellName) || /conditionalPagesCount/.test(cellName)
}

/**
 *  Make template for searching cells by part of DOM element id
 *
 * @param partOfId Example: plan, eaModules0.sections0.topics0 ...
 * @returns {string}
 */
function getSearchSelectorTemplate(partOfId) {
    return "input[id*='" + partOfId + "']"
}

