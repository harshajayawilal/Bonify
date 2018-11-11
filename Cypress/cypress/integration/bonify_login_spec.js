describe ('my.bonify.de login Test', ()=> {

    var validFname = "Harsha";
    var validEmail = "emofox21@gmail.com";
    var validPassword = "@bonify123";
    

    describe ('Login Related Tests', ()=> {
        beforeEach(function(){
          console.log('Test Started.')
          cy.visit('https://my.bonify.de/login?')
          cy.contains('Willkommen bei bonify!')
        })

        afterEach(function(){
          console.log('Test Ended.')
        })

        //Test -01
        it('User cannot login without entering an email and a password', ()=> {
            cy.get('input[name="email"]').clear()
            cy.contains('Senden').click()

            cy.contains('Trage bitte Deine Email-Adresse ein')

            cy.get('input[name="email"]').clear()
            .type("someemail@email.com")
            .should('have.value', "someemail@email.com")
            cy.contains('Senden').click()

            cy.contains('Trage bitte Dein Passwort ein')
        })

        //Test -02
        it('User cannot provide an incorrect email as username', ()=> {
            cy.get('input[name="email"]').clear()
              .type("111")
              .should('have.value', "111")

            cy.contains('Senden').click()

            cy.contains('Bitte nutze eine gültige Email-Adresse')

        })
        
        //Test -03
        it('User cannot login using incorrect user credentials', ()=> {
          
            cy.get('input[name="email"]').clear()
              .type(validEmail)
              .should('have.value', validEmail)
            cy.get('input[name="password"]').clear()
              .type("password123")
              .should('have.value', "password123")
            cy.contains('Senden').click()

            cy.contains('Leider passen Benutzername und Passwort nicht zusammen.')


        })

        //Test -04
        it('User can sucessfully login using correct user credentials', ()=> {
          
            cy.get('input[name="email"]').clear()
              .type(validEmail)
              .should('have.value', validEmail)
            cy.get('input[name="password"]').clear()
              .type(validPassword)
              .should('have.value', validPassword)
            cy.contains('Senden').click()

            cy.contains('Hey '+ validFname +', schön Dich zu sehen!')

            cy.get('.main-toolbar > .personal-info > .dropdown > button > .dropdown-label > .right').click()
            cy.get('.open > :nth-child(4) > .dropdown-item-content > :nth-child(2)').click()
            
        })

        //Test -05
        it('User can request to reset password via forgot password', ()=> {
            cy.contains('Passwort vergessen?').click()

            cy.contains('Email senden')
            
            cy.get('input[name="email"]').clear()
              .type(validEmail)
              .should('have.value', validEmail)

            cy.contains('Senden').click()

            cy.contains('Vielen Dank! Wenn diese Email-Adresse bei bonify hinterlegt ist, haben wir eine Email mit einem Link zum Zurücksetzen des Passwortes verschickt. Diese sollte in den nächsten Sekunden bei Dir eintreffen.')


        })

        //Test -06
        it('Email validations on forgot password', ()=> {
            cy.contains('Passwort vergessen?').click()

            cy.contains('Email senden')

            cy.contains('Senden').click()
            cy.contains('Trage bitte Deine Email-Adresse ein')
            
            cy.get('input[name="email"]').clear()
            .type("111")
            .should('have.value', "111")
            cy.contains('Senden').click()
            cy.contains('Bitte nutze eine gültige Email-Adresse')

            cy.contains('Zurück').click()
            cy.contains('Willkommen bei bonify!')

        })

        //Test -07
        it('User can visit Impressum page from login page', ()=> {
          cy.contains('Impressum').click()

          cy.contains('bonify ist ein Produkt der Forteil GmbH')

        })

        //Test -08
        it('User can visit AGB page from login page', ()=> {
            cy.contains('AGB').click()

            cy.contains('der Forteil GmbH')

        })

        //Test -09
        it('User can visit Datenschutz page from login page', ()=> {
            cy.contains('Datenschutz').click()

            cy.contains('Datenschutz')

        })

    })

})
