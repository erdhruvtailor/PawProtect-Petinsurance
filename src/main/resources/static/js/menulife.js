function deletePet(pet) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch('/pet/pet/' + pet, {
                method: 'DELETE'
            }).then(response => {
                if (response.ok) {
                    Swal.fire(
                        'Deleted!',
                        'Your sweet Pet has been deleted.',
                        'success'
                    ).then(() => {
                        window.location.reload();
                    });
                } else {
                    Swal.fire(
                        'Error!',
                        'There was a problem deleting! its already being used in the policy.',
                        'error'
                    );
                }
            });
        }
    });
}

function editPet(item) {
    Swal.fire({
        title: 'Edit Pet',
        input: 'text',
        inputValue: item,
        showCancelButton: true,
        confirmButtonText: 'Save',
        showLoaderOnConfirm: true,
        preConfirm: (newItem) => {
            // Logic to update the item
            // This should send an update request to the server
            return fetch('/petForm?id=' + item, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({newItem: newItem})
            }).then(response => {
                if (!response.ok) {
                    throw new Error(response.statusText);
                }
                return response.json();
            }).catch(error => {
                Swal.showValidationMessage(
                    `Request failed: ${error}`
                );
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                'Updated!',
                'Your item has been updated.',
                'success'
            ).then(() => {
                window.location.reload();
            });
        }
    });
}


document.addEventListener('DOMContentLoaded', (event) => {
    const policyPeriod = document.getElementById('policy-period');
    const annualLimit = document.getElementById('annual-limit');
    const reimbursementRateRadios = document.getElementsByName('reimbursementRate');
    const annualDeductibleRadios = document.getElementsByName('annualDeductible');
    /*const coverageRadios = document.getElementsByName('coverage');*/


    const grandTotalField = document.getElementById('grandTotal');
    const displayTotal = document.getElementById('displayTotal');

    function calculateTotal() {
        let policyPeriodValue = parseInt(policyPeriod.value) || 0;
        let annualLimitValue = parseInt(annualLimit.value) || 0;
        let reimbursementRateValue = Array.from(reimbursementRateRadios).find(r => r.checked)?.value || 0;
        let annualDeductibleValue = Array.from(annualDeductibleRadios).find(r => r.checked)?.value || 0;
        /*let coverageValue = Array.from(coverageRadios).find(r => r.checked)?.value || 0;
        coverageValue = (coverageValue === "Accident + Illness") ? 1.5 : 1;*/

        // Example calculation (replace with your logic)
        // let total = policyPeriodValue * parseInt(coverageValue) * annualLimitValue + parseInt(reimbursementRateValue) + parseInt(annualDeductibleValue);
        let total = policyPeriodValue * annualLimitValue + parseInt(reimbursementRateValue) + parseInt(annualDeductibleValue);

        /*console.log('Coverage:', coverageValue);*/
        console.log('Policy Period:', policyPeriodValue);
        console.log('Annual Limit:', annualLimitValue);
        console.log('Reimbursement Rate:', reimbursementRateValue);
        console.log('Annual Deductible:', annualDeductibleValue);
        console.log('Total:', total);

        // Update the hidden field and the displayed total
        grandTotalField.value = total;
        displayTotal.textContent = total;
    }

    // Attach change event listeners to the form inputs
    policyPeriod.addEventListener('change', calculateTotal);
    annualLimit.addEventListener('change', calculateTotal);
    reimbursementRateRadios.forEach(radio => radio.addEventListener('change', calculateTotal));
    annualDeductibleRadios.forEach(radio => radio.addEventListener('change', calculateTotal));
    /*coverageRadios.forEach(radio => radio.addEventListener('change', calculateTotal));*/

    // Initial calculation
    calculateTotal();
});

