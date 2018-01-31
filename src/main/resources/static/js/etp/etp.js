$(function () {
    // TODO : пересчет елементов при обновление страины(((
    $("input[id*='plan']").each(function (index, element) {
        element.onchange = calcTotal
        isIntegerField(element.name) ? getIntegerMask().mask(element) : getFloatMask().mask(element)
        // if(!/totalHours/.test(element.name)) {
        //     // $('#' + element.id).trigger('change')
        //     $(getSearchSelectorTemplate(element.id)).trigger('change')
        // }
    })

    // $("input[id*='plan']").filter(function (index, element) {
    //     return !(/totalHours/.test(element.name))
    // }).trigger('change')
})

function getFloatMask() {
    return new Inputmask('decimal', {
        digits: 2,
        allowMinus: false,
        placeholder: '0.0'
    })
}

function getIntegerMask() {
    return new Inputmask('integer', {
        allowMinus: false,
        placeholder: '0'
    })
}

function calcTotal(e) {
    var idAsArray = e.target.id.split('.')
    var rowNameTemplate = idAsArray.slice(0, idAsArray.length -1).join('.')
    var columnNameTemplate = idAsArray[idAsArray.length - 1]

    if(!isIntegerField(columnNameTemplate)) {
        calcTotalRow(rowNameTemplate)
    }
    calcTotalColumn(columnNameTemplate)
}

function calcTotalRow(rowName) {
    var rowSelector = getSearchSelectorTemplate(rowName)

    console.log(rowName)

    var total = 0.0
    $(rowSelector).each(function (i, e) {
        if(!isIntegerField(e.name)) {
            total += parseFloat(e.value)
        }
    })

    $("input[id='" + rowName + ".totalHours']").val(total)

    calcTotalColumn('totalHours')
}

function calcTotalColumn(colName) {
    var colSelector = getSearchSelectorTemplate(colName)

    var emaTotal = 0.0,
        omaTotal = 0.0,
        eaTotal = 0.0

    $(colSelector).each(function (i, e) {
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
                if(e.value) {
                    eaTotal += parseFloat(e.value)
                }
                break
            default: break
        }
    })

    $('#emaModuleTotalRow\\.' + colName).val(emaTotal)
    $('#omaModuleTotalRow\\.' + colName).val(omaTotal)
    $('#eaModuleTotalRow\\.' + colName).val(eaTotal)
    $('#etpTotalRow\\.' + colName).val((emaTotal + omaTotal + eaTotal))
}

function isIntegerField(cellName) {
    return /lernerCount/.test(cellName) || /groupCount/.test(cellName) || /conditionalPagesCount/.test(cellName)  //|| /totalHours/.test(cellName)
}

function getSearchSelectorTemplate(partOfId) {
    return "input[id*='" + partOfId + "']"
}

