Ext.require([
    'Ext.form.*',
    'Ext.Img',
    'Ext.tip.QuickTipManager'
]);

Ext.onReady(
function(){
Ext.create('Ext.form.Panel', {
    title: 'Basic Form',
    renderTo: Ext.getBody(),
    bodyPadding: 10,
    width: 300,

    url: 'save-form.php',

    items: [{
        xtype: 'textfield',
        name: 'username',
        fieldLabel: '用户名',
        allowBlank: false,
        minLength: 6
    },
    {
        xtype: 'textfield',
        name: 'password',
        fieldLabel: '密码',
        inputType: 'password',
        allowBlank: false,
        minLength: 6
    }],

    buttons: [{
        text: 'Submit',
        handler: function() {
            // The getForm() method returns the Ext.form.Basic instance:
            var form = this.up('form').getForm();
            if (form.isValid()) {
                // Submit the Ajax request and handle the response
                form.submit({
                    success: function(form, action) {
                       Ext.Msg.alert('Success', action.result.msg);
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('Failed', action.result.msg);
                    }
                });
            }
        }
    }]
})});

