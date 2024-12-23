// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrl: 'http://localhost:9090',
  apiSessionUrl: 'http://localhost:9090/session',
  apiParticipantUrl: 'http://localhost:9090/participant',
  baseUrlAuth : 'http://backend:9090/auth/',
  apiServerUrlDomaine: 'http://localhost:9090/domaine',
  apiServerUrlFormateur: 'http://localhost:9090/formateur',
  apiServerUrlFormation: 'http://localhost:9090/formation',
  apiServerUrlOrganisme: 'http://localhost:9090/organisme',
  apiServerUrlParticipant: 'http://localhost:9090/participant',
  apiServerUrlPays: 'http://localhost:9090/pays',
  apiServerUrlProfil: 'http://localhost:9090/profil',




   // Utilisé pour ng serve
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
