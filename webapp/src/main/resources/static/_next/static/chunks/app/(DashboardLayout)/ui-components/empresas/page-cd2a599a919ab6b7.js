(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[643],{6718:function(e,o,i){"use strict";var a=i(6314);o.Z=void 0;var r=a(i(984)),l=i(7437),s=(0,r.default)((0,l.jsx)("path",{d:"M16.59 7.58 10 14.17l-3.59-3.58L5 12l5 5 8-8zM12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8z"}),"CheckCircleOutline");o.Z=s},6446:function(e,o,i){"use strict";var a=i(6314);o.Z=void 0;var r=a(i(984)),l=i(7437),s=(0,r.default)((0,l.jsx)("path",{d:"M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"}),"Delete");o.Z=s},3391:function(e,o,i){"use strict";var a=i(6314);o.Z=void 0;var r=a(i(984)),l=i(7437),s=(0,r.default)((0,l.jsx)("path",{d:"M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34a.9959.9959 0 0 0-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"}),"Edit");o.Z=s},8851:function(e,o,i){Promise.resolve().then(i.bind(i,1908))},8312:function(e,o,i){"use strict";var a=i(7437);i(2265);var r=i(5133),l=i(8469),s=i(3226),n=i(3457),t=i(6507);o.Z=e=>{let{title:o,subtitle:i,children:c,action:d,footer:p,cardheading:h,headtitle:u,headsubtitle:m,middlecontent:x}=e;return(0,a.jsxs)(r.Z,{sx:{padding:0},elevation:9,variant:void 0,children:[h?(0,a.jsxs)(l.Z,{children:[(0,a.jsx)(s.Z,{variant:"h4",children:u}),(0,a.jsx)(s.Z,{variant:"subtitle2",color:"textSecondary",children:m})]}):(0,a.jsxs)(l.Z,{sx:{p:"30px"},children:[o?(0,a.jsxs)(n.Z,{direction:"row",spacing:2,justifyContent:"space-between",alignItems:"center",mb:3,children:[(0,a.jsxs)(t.Z,{children:[o?(0,a.jsx)(s.Z,{variant:"h4",children:o}):"",i?(0,a.jsx)(s.Z,{variant:"subtitle2",color:"textSecondary",children:i}):""]}),d]}):null,c]}),x,p]})}},1908:function(e,o,i){"use strict";i.r(o);var a=i(7437),r=i(2265),l=i(8874),s=i(6507),n=i(3724),t=i(9394),c=i(1797),d=i(6337),p=i(3457),h=i(1975),u=i(4081),m=i(2510),x=i(4997),j=i(4309),v=i(2834),Z=i(3701),f=i(6988),b=i(8489),g=i(666),E=i(9279),C=i(3391),y=i(6446),k=i(9108),w=i(4273),T=i(8312),z=i(6718);o.default=()=>{let[e,o]=(0,r.useState)(!1),[i,A]=(0,r.useState)(!1),[S,P]=(0,r.useState)(null),[N,W]=(0,r.useState)(!1),[D,I]=(0,r.useState)(""),[O,_]=(0,r.useState)([]),[B,F]=(0,r.useState)({id:"",nombre:"",domicilio:"",telefono:"",correo:"",tipoEmpresa:""}),M=async e=>{try{let o="Basic "+btoa("sven:pass"),i=await fetch("http://localhost:8080/restful/objects/vidrios.Empresa/".concat(e.id),{method:"GET",headers:{"Content-Type":'application/json;profile="urn:org.apache.isis"',Authorization:o,accept:"application/json;profile=urn:org.apache.isis/v2;suppress=all"}});if(i.ok){let o=await i.json();F({id:o.id,nombre:o.name,domicilio:o.domicilio,telefono:o.telefono,correo:o.correo,tipoEmpresa:o.tipoEmpresa}),P(e.id),A(!0)}else console.error("Error al obtener los datos de la empresa")}catch(e){console.error("Error al realizar la solicitud:",e)}},V=()=>{o(!1)},L=()=>{A(!1)};(0,r.useEffect)(()=>{let e=async()=>{try{let e="Basic "+btoa("sven:pass"),o=await fetch("http://localhost:8080/restful/services/simple.Empresas/actions/VerEmpresas/invoke",{method:"GET",headers:{"Content-Type":'application/json;profile="urn:org.apache.isis/v2"',Authorization:e,accept:"application/json;profile=urn:org.apache.isis/v2"}}),i=await o.json();_(i)}catch(e){console.error("Error al realizar la solicitud:",e)}};e()},[]);let G=e=>{if("target"in e){let{name:o,value:i}=e.target;console.log("TextField Change - name: ".concat(o,", value: ").concat(i)),F(e=>({...e,[o||""]:i}))}else{var o;let i=(null===(o=e.target)||void 0===o?void 0:o.value)||"";console.log("Select Change - selectedValue: ".concat(i)),F(e=>({...e,tipoEmpresa:i}))}},H=async()=>{try{let e="Basic "+btoa("sven:pass"),o=await fetch("http://localhost:8080/restful/services/simple.Empresas/actions/CrearEmpresa/invoke",{method:"POST",headers:{"Content-Type":'application/json;profile="urn:org.apache.isis"',Authorization:e,accept:"application/json;profile=urn:org.apache.isis/v2;suppress=all"},body:JSON.stringify({nombre:{value:B.nombre},domicilio:{value:B.domicilio},telefono:{value:B.telefono},correo:{value:B.correo},tipoEmpresa:{value:B.tipoEmpresa}})});o.ok?window.location.reload():console.error("Error al enviar datos a la base de datos.")}catch(e){console.error("Error al realizar la solicitud:",e)}},J=async()=>{try{let e="Basic "+btoa("sven:pass"),o=await fetch("http://localhost:8080/restful/services/simple.Reportes/actions/generarReporteEmpresas/invoke",{method:"GET",headers:{"Content-Type":'application/json;profile="urn:org.apache.isis"',Authorization:e,accept:"application/json;profile=urn:org.apache.isis/v2;suppress=all"}}),i=await o.json();if(console.log(i),i&&i.result&&i.result.value){let e=i.result.value;window.open(e,"_blank")}else console.error("La respuesta del servidor no contiene el enlace al PDF.")}catch(e){console.error("Error al realizar la solicitud:",e)}},R=async()=>{try{let e="Basic "+btoa("sven:pass"),o=await fetch("http://localhost:8080/restful/objects/vidrios.Empresa/".concat(B.id,"/actions/updateName/invoke"),{method:"PUT",headers:{"Content-Type":'application/json;profile="urn:org.apache.isis"',Authorization:e,accept:"application/json;profile=urn:org.apache.isis/v2;suppress=all"},body:JSON.stringify({nombre:{value:B.nombre},domicilio:{value:B.domicilio},telefono:{value:B.telefono},correo:{value:B.correo},tipoEmpresa:{value:B.tipoEmpresa}})});o.ok?window.location.reload():console.error("Error al enviar datos a la base de datos.")}catch(e){console.error("Error al realizar la solicitud:",e)}},U=async(e,o)=>{try{let i="http://localhost:8080/restful/objects/vidrios.Empresa/".concat(e,"/actions/").concat(o?"delete":"activar","/invoke"),a="Basic "+btoa("sven:pass"),r=await fetch(i,{method:"POST",headers:{"Content-Type":'application/json;profile="urn:org.apache.isis"',Authorization:a,accept:"application/json;profile=urn:org.apache.isis/v2;suppress=all"}});r.ok?I('Acci\xf3n "'.concat(o?"Desactivar":"Activar",'" completada con \xe9xito')):I("Error al realizar la acci\xf3n: ".concat(r.statusText)),W(!0)}catch(e){I("Error al realizar la solicitud"),W(!0)}},q=[{id:"Cliente",nombre:"Cliente"},{id:"Proveedor",nombre:"Proveedor"}];return(0,a.jsx)(l.ZP,{container:!0,spacing:0,children:(0,a.jsxs)(l.ZP,{item:!0,xs:12,lg:12,children:[(0,a.jsxs)(s.Z,{display:"flex",justifyContent:"space-between",children:[(0,a.jsx)(n.Z,{variant:"contained",sx:{margin:"20px"},onClick:()=>{o(!0)},children:"Agregar Empresa"}),(0,a.jsx)(n.Z,{variant:"contained",sx:{margin:"20px",backgroundColor:w.Z[500],"&:hover":{backgroundColor:w.Z[700]}},startIcon:(0,a.jsx)(k.Z,{}),onClick:J,children:"Exportar a PDF"})]}),(0,a.jsxs)(t.Z,{open:e,onClose:V,children:[(0,a.jsx)(c.Z,{children:"Agregar Empresa"}),(0,a.jsx)(d.Z,{sx:{width:"600px",textAlign:"center"},children:(0,a.jsx)(l.ZP,{item:!0,xs:12,lg:12,children:(0,a.jsx)(T.Z,{title:"Complete el Formulario de la Empresa",children:(0,a.jsxs)(a.Fragment,{children:[(0,a.jsxs)(p.Z,{spacing:3,children:[(0,a.jsx)(h.Z,{id:"nombre",label:"Nombre",name:"nombre",variant:"outlined",fullWidth:!0,value:B.nombre,onChange:G}),(0,a.jsx)(h.Z,{id:"domicilio",label:"Domicilio",name:"domicilio",variant:"outlined",fullWidth:!0,value:B.domicilio,onChange:G}),(0,a.jsx)(h.Z,{id:"telefono",label:"Telefono",name:"telefono",variant:"outlined",fullWidth:!0,value:B.telefono,onChange:G}),(0,a.jsx)(h.Z,{id:"email",label:"Correo",name:"correo",variant:"outlined",fullWidth:!0,value:B.correo,onChange:G}),(0,a.jsxs)(u.Z,{fullWidth:!0,children:[(0,a.jsx)(m.Z,{id:"modelo-label",children:"Tipo de Empresa"}),(0,a.jsx)(x.Z,{labelId:"tipoempresa-label",id:"tipoempresa",label:"TipoEmpresa",name:"tipoEmpresa",value:B.tipoEmpresa,onChange:G,children:q.map(e=>(0,a.jsx)(j.Z,{value:e.id,children:e.nombre},e.id))})]})]}),(0,a.jsx)("br",{}),(0,a.jsx)(n.Z,{onClick:H,children:"Cargar"})]})})})}),(0,a.jsx)(v.Z,{children:(0,a.jsx)(n.Z,{onClick:V,children:"Cancelar"})})]}),(0,a.jsx)(T.Z,{title:"Empresas",children:(0,a.jsxs)(Z.Z,{children:[(0,a.jsx)(f.Z,{children:(0,a.jsxs)(b.Z,{children:[(0,a.jsx)(g.Z,{children:"ID"}),(0,a.jsx)(g.Z,{children:"Nombre"}),(0,a.jsx)(g.Z,{children:"Tipo de Empresa"}),(0,a.jsx)(g.Z,{children:"Telefono"}),(0,a.jsx)(g.Z,{children:"Correo"}),(0,a.jsx)(g.Z,{children:"Domicilio"}),(0,a.jsx)(g.Z,{children:"Activo"}),(0,a.jsx)(g.Z,{children:"Acciones"})]})}),(0,a.jsx)(E.Z,{children:O.map(e=>(0,a.jsxs)(b.Z,{children:[(0,a.jsx)(g.Z,{children:e.id}),(0,a.jsx)(g.Z,{children:e.name}),(0,a.jsx)(g.Z,{children:e.tipoEmpresa}),(0,a.jsx)(g.Z,{children:e.telefono}),(0,a.jsx)(g.Z,{children:e.correo}),(0,a.jsx)(g.Z,{children:e.domicilio}),(0,a.jsx)(g.Z,{children:e.activo?"S\xed":"No"}),(0,a.jsxs)(g.Z,{children:[(0,a.jsx)(n.Z,{startIcon:(0,a.jsx)(C.Z,{}),color:"primary",onClick:()=>M(e),children:"Editar"}),(0,a.jsxs)(t.Z,{open:i,onClose:L,children:[(0,a.jsx)(c.Z,{children:"Editar Empresa"}),(0,a.jsx)(d.Z,{sx:{width:"600px",textAlign:"center"},children:(0,a.jsx)(l.ZP,{item:!0,xs:12,lg:12,children:(0,a.jsx)(T.Z,{title:"Editar Empresa",children:(0,a.jsxs)(a.Fragment,{children:[(0,a.jsxs)(p.Z,{spacing:3,children:[(0,a.jsx)(h.Z,{id:"nombre",name:"nombre",label:"Nombre",variant:"outlined",fullWidth:!0,value:B.nombre,onChange:G}),(0,a.jsx)(h.Z,{id:"domicilio",name:"domicilio",label:"Domicilio",variant:"outlined",fullWidth:!0,value:B.domicilio,onChange:G}),(0,a.jsx)(h.Z,{id:"telefono",name:"telefono",label:"Telefono",variant:"outlined",fullWidth:!0,value:B.telefono,onChange:G}),(0,a.jsx)(h.Z,{id:"email",name:"correo",label:"Correo",variant:"outlined",fullWidth:!0,value:B.correo,onChange:G}),(0,a.jsxs)(u.Z,{fullWidth:!0,children:[(0,a.jsx)(m.Z,{id:"modelo-label",children:"Tipo de Empresa"}),(0,a.jsx)(x.Z,{labelId:"tipoempresa-label",id:"tipoempresa",name:"tipoEmpresa",label:"TipoEmpresa",value:B.tipoEmpresa,onChange:G,children:q.map(e=>(0,a.jsx)(j.Z,{value:e.id,children:e.nombre},e.id))})]})]}),(0,a.jsx)("br",{}),(0,a.jsx)(n.Z,{onClick:R,children:"Confirmar Edici\xf3n"})]})})})}),(0,a.jsx)(v.Z,{children:(0,a.jsx)(n.Z,{onClick:L,children:"Cancelar"})})]}),(0,a.jsx)(n.Z,{startIcon:e.activo?(0,a.jsx)(y.Z,{}):(0,a.jsx)(z.Z,{}),color:e.activo?"secondary":"success",onClick:()=>U(e.id,e.activo),children:e.activo?"Desactivar":"Activar"}),(0,a.jsx)(t.Z,{open:N,onClose:()=>W(!1),children:(0,a.jsxs)(d.Z,{children:[D,(0,a.jsx)(n.Z,{onClick:()=>window.location.reload(),children:"Aceptar"})]})})]})]},e.id))})]})})]})})}}},function(e){e.O(0,[370,468,238,874,659,756,413,798,869,971,596,744],function(){return e(e.s=8851)}),_N_E=e.O()}]);